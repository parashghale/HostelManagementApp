package com.parash.hostelmanagementapp;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class loginTesting {
    @Rule
    public ActivityTestRule<LoginActivity> testRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);

    private LoginActivity loginActivity = null;

    @Before
    public void setUp() throws Exception {
        loginActivity = testRule.getActivity();
    }

    @Test
    public void loginTest(){
        onView(withId(R.id.etusername))
                .perform(typeText("parash@gmail.com"));
        closeSoftKeyboard();
        onView(withId(R.id.etpassword))
                .perform(typeText("password"));
        closeSoftKeyboard();
        onView(withId(R.id.btnLogin))
                .perform(click());

    }

    @Test
    public void validationTest(){
        onView(withId(R.id.etusername))
                .perform(typeText("parash@gmail.com"));
        closeSoftKeyboard();
        onView(withId(R.id.etpassword))
                .perform(typeText(""));
        closeSoftKeyboard();
        onView(withId(R.id.btnLogin))
                .perform(click());


    }
    @After
    public void tearDown() throws Exception {
    }

}

