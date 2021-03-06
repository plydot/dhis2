package org.hisp.dhis.customvalue;

/*
 * Copyright (c) 2004-2009, University of Oslo
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 * * Neither the name of the HISP project nor the names of its contributors may
 *   be used to endorse or promote products derived from this software without
 *   specific prior written permission.
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

import java.util.Collection;

import org.hisp.dhis.dataelement.DataElement;
import org.hisp.dhis.dataelement.DataElementCategoryCombo;
import org.hisp.dhis.dataelement.DataElementCategoryOptionCombo;
import org.hisp.dhis.dataset.DataSet;

/**
 * @author Latifov Murodillo Abdusamadovich
 * 
 * @version $Id$
 */
public interface CustomValueService
{
    String ID = CustomValue.class.getName();

    /**
     * 
     * @param customValue
     * @return The generated unique identifier for this CustomValue.
     */
    int addCustomValue( CustomValue customValue );

    /**
     * Deletes a CustomValue.
     * 
     * @param customValue. The CustomValue to delete.
     */
    void deleteCustomValue( CustomValue customValue );

    /**
     * Get all CustomValues by DataSet.
     * 
     * @return A collection containing all CustomValues within given dataset.
     */
    Collection<CustomValue> getCustomValuesByDataSet( DataSet dataSet );

    /**
     * Get all CustomValues by DataElement.
     * 
     * @return A collection containing all CustomValues within given dataelement.
     */
    Collection<CustomValue> getCustomValuesByDataElement( DataElement dataElement );

    /**
     * Get all CustomValues by DataElementCategoryCombo.
     * 
     * @return A collection containing all CustomValues within given categorycombo.
     */
    Collection<CustomValue> getCustomValuesByCategoryCombo( DataElementCategoryCombo categoryCombo );

    /**
     * Get all CustomValues by DataSet, DataElement, DataElementCategoryCombo.
     * 
     * @return A collection containing all CustomValues in a given criteria.
     */
    Collection<CustomValue> getCustomValues( DataSet dataSet, DataElement dataElement,
        DataElementCategoryOptionCombo dataElementCategoryOptionCombo );

    /**
     * Get CustomValue by Id.
     * 
     * @return CustomValue object.
     */
    CustomValue getCustomValue( int id );
    
    /**
     * Get CustomValue by value.
     * 
     * @return A collection containing found CustomValues.
     */
    Collection<CustomValue> findCustomValues( String searchValue );
    
}
