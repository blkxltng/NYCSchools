package com.blkxltng.a20200526_mauricegaynor_nycschools;

import android.os.Parcel;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import com.blkxltng.a20200526_mauricegaynor_nycschools.models.SchoolInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests that the parcelable interface is implemented correctly.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class ParcelizeSchoolInfoTest {

    public static final String TEST_DBN = "0X4H8P";
    public static final String TEST_SCHOOL_NAME = "My High School";
    public static final String TEST_SCHOOL_NUMBER = "(123) 456-7890";
    private SchoolInfo mSchoolInfo;

    @Before
    public void createSchoolInfo() {
        mSchoolInfo = new SchoolInfo();
    }

    @Test
    public void schoolInfo_ParcelableWriteRead() {
        // Set up the Parcelable object
        mSchoolInfo.setDbn(TEST_DBN);
        mSchoolInfo.setSchool_name(TEST_SCHOOL_NAME);
        mSchoolInfo.setPhone_number(TEST_SCHOOL_NUMBER);

        // Write the data
        Parcel parcel = Parcel.obtain();
        mSchoolInfo.writeToParcel(parcel, mSchoolInfo.describeContents());

        // After you're done with writing, you need to reset the parcel for reading.
        parcel.setDataPosition(0);

        // Read the data
        SchoolInfo createdFromParcel = SchoolInfo.CREATOR.createFromParcel(parcel);

        // Verify that the received data is correct.
        assertEquals(TEST_DBN, createdFromParcel.getDbn());
        assertEquals(TEST_SCHOOL_NAME, createdFromParcel.getSchool_name());
        assertEquals(TEST_SCHOOL_NUMBER, createdFromParcel.getPhone_number());
        assertNull(createdFromParcel.getLocation());
        assertNull(createdFromParcel.getSchool_email());
        assertNull(createdFromParcel.getTotal_students());
    }
}
