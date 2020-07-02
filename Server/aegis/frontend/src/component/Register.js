import React, { useState } from 'react';
import axios from 'axios';
import CryptoJS from 'crypto-js';
import {validatePassword,validateCredentials} from '../utils/Validator';
import {encrypt} from '../utils/Cryptography';

function Register(props){
    const PASSWORD              = "This is a password";
    const username              = useFormInput('');
    const password              = useFormInput('');
    const confPassword          = useFormInput('');
    const [error, setError]     = useState(null);
    const [loading, setLoading] = useState(false);

    // handle button lick of register form
    const handleRegister = () => {
        setError(null);
        if(validateCredentials(username.value.trim(),password.value.trim()) && validatePassword(password.value.trim(), confPassword.value.trim())){
            setLoading(true);
            var un = username.value.trim().toString();
            var pw = password.value.trim().toString();
            var iv      = CryptoJS.lib.WordArray.random(128/8).toString(CryptoJS.enc.Hex);
            var salt    = CryptoJS.lib.WordArray.random(128/8).toString(CryptoJS.enc.Hex);
            
            var ciphertext = encrypt(128,1000,salt,iv,PASSWORD,pw);
            var aesPassword = (iv+"::"+salt+"::"+ciphertext);
            console.log("AES:",aesPassword);
            pw    = btoa(aesPassword);
            
            
            var data = {username: un, password: pw}
            console.log(data);
            axios.post('http://localhost:8080/api/registerEnc',  data).then(response =>{
                setLoading(false);
                props.history.push('/');
            }).catch(error =>{
                  setLoading(false);
                  setError("Can not use that username.");
            });
        }else{
            setLoading(false);
            setError("Passwords are empty or do not match.");
        }
    }

    const Login = () =>{
        props.history.push("/")
      }

    return (
        <div>
            Register<br /><br />
            <div>
                Username<br />
                <input type="text" {...username} autoComplete="new-password" />
            </div>
            <div style={{marginTop: 10}}>
                Password<br />
                <input type="password" {...password} autoComplete="new-password" />
            </div>
            <div style={{marginTop: 10}}>
                Password<br />
                <input type="password" {...confPassword} autoComplete="new-password"/>
            </div>
            {error && <><small style={{ color: 'red' }}>{error}</small><br /></>}<br />
            <input type="button" value={loading ? 'Loading...' : 'Register'} onClick={handleRegister} disabled={loading} /><br />
            <br />
      <input type="button" onClick={Login} value="Already have an account?"/><br />
        
        </div>
    );
}

const useFormInput = initialValue => {
    const [value, setValue] = useState(initialValue);
   
    const handleChange = e => {
      setValue(e.target.value);
    }
    return {
      value,
      onChange: handleChange
    }
  }
   
  export default Register;