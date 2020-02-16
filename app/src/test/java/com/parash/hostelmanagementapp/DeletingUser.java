package com.parash.hostelmanagementapp;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class DeletingUser {

    @Test
    public void Deleteing(){
        FirebaseUser auth = Mockito.mock(FirebaseUser.class);
        final Task<Void> mockedAuth = Mockito.mock(Task.class);
        when(auth.delete("")
        .thenReturn(mockedAuth);
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Task<Void> authResult = invocation.getArgument(0, Task.class);
                assertEquals(true,authResult.isSuccessful());
                return null;
            }
        });

    }

}
