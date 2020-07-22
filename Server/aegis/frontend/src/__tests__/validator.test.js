import {validateCredentials,validatePassword} from '../utils/Validator';

test('validating valid credentials',()=>{
    //removeUserSession();
    expect(validateCredentials('person1',"password1")).toBeTruthy();
});

test('validating invalid credentials',()=>{
    //removeUserSession();
    expect(validateCredentials('',"")).toBeFalsy();
});

test('validating valid passwords',()=>{
    //removeUserSession();
    expect(validatePassword('password1',"password1")).toBeTruthy();
});

test('validating invalid passwords',()=>{
    //removeUserSession();
    expect(validatePassword('password1',"password2")).toBeFalsy();
});