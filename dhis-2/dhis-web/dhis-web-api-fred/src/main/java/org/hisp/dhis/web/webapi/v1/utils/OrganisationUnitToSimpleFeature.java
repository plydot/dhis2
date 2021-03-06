package org.hisp.dhis.web.webapi.v1.utils;

/*
 * Copyright (c) 2004-2013, University of Oslo
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * Neither the name of the HISP project nor the names of its contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import org.geotools.data.DataUtilities;
import org.geotools.feature.SchemaException;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.hisp.dhis.organisationunit.OrganisationUnit;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Morten Olav Hansen <mortenoh@gmail.com>
 */
@Component
public class OrganisationUnitToSimpleFeature implements Converter<OrganisationUnit, SimpleFeature>
{
    private final GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();

    private final SimpleFeatureType featureType;

    public OrganisationUnitToSimpleFeature() throws SchemaException
    {
        featureType = DataUtilities.createType( "Location",
            "location:Point:srid=4326,id:String,name:String,active:Boolean" );
    }

    @Override
    public SimpleFeature convert( OrganisationUnit organisationUnit )
    {
        SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder( featureType );

        if ( organisationUnit.getCoordinates() != null && !organisationUnit.getCoordinates().isEmpty() )
        {
            GeoUtils.Coordinates coordinates = GeoUtils.parseCoordinates( organisationUnit.getCoordinates(), GeoUtils.CoordinateOrder.COORDINATE_LNGLAT );

            if ( coordinates.lng == null || coordinates.lat == null )
            {
                return null;
            }

            Coordinate coordinate = new Coordinate( coordinates.lng, coordinates.lat );
            Point point = geometryFactory.createPoint( coordinate );
            featureBuilder.add( point );

            featureBuilder.add( organisationUnit.getUid() );
            featureBuilder.add( organisationUnit.getName() );
            featureBuilder.add( organisationUnit.isActive() );

            return featureBuilder.buildFeature( null );
        }

        return null;
    }
}
