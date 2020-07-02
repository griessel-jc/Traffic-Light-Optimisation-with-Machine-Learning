import React, { useState } from 'react';
import { setUserSession } from '../utils/Common';
import { validateCredentials } from '../utils/Validator';
import CryptoJS from 'crypto-js';
import axios from 'axios';
function Login(props) {
  const PASSWORD              = "This is a password";
  const username = useFormInput('');
  const password = useFormInput('');
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);


  class AesUtil{
    constructor(keySize, iterationCount) {
        this.keySize = keySize / 32;
        this.iterationCount = iterationCount;
    }    
};
AesUtil.prototype.generateKey = function (salt, passPhrase) {
    var key = CryptoJS.PBKDF2(
        passPhrase,
        CryptoJS.enc.Hex.parse(salt),
        { keySize: this.keySize, iterations: this.iterationCount });
    return key;
}

AesUtil.prototype.encrypt = function (salt, iv, passPhrase, plainText) {
    var key = this.generateKey(salt, passPhrase);
    var encrypted = CryptoJS.AES.encrypt(
        plainText,
        key,
        { iv: CryptoJS.enc.Hex.parse(iv) });
    return encrypted.ciphertext.toString(CryptoJS.enc.Base64);
}

AesUtil.prototype.decrypt = function (salt, iv, passPhrase, cipherText) {
    var key = this.generateKey(salt, passPhrase);
    var cipherParams = CryptoJS.lib.CipherParams.create({
        ciphertext: CryptoJS.enc.Base64.parse(cipherText)
    });
    var decrypted = CryptoJS.AES.decrypt(
        cipherParams,
        key,
        { iv: CryptoJS.enc.Hex.parse(iv) });
    return decrypted.toString(CryptoJS.enc.Utf8);
}

  // handle button click of login form
  const handleLogin = () => {
    setError(null);
    
    if (validateCredentials(username.value.trim(), password.value.trim())) {
      setLoading(true);
      var un = username.value.trim().toString();
            var pw = password.value.trim().toString();
            var iv      = CryptoJS.lib.WordArray.random(128/8).toString(CryptoJS.enc.Hex);
            var salt    = CryptoJS.lib.WordArray.random(128/8).toString(CryptoJS.enc.Hex);
            var aesUtil = new AesUtil(128,1000);
            var ciphertext = aesUtil.encrypt(salt,iv,PASSWORD,pw);
            var aesPassword = (iv+"::"+salt+"::"+ciphertext);
            console.log("AES:",aesPassword);
            pw    = btoa(aesPassword);
            
            
            var data = {username: un, password: pw}
            console.log(data);
      axios.post('http://localhost:8080/api/login',data).then(response => {
        setLoading(false);
        const data = response.data;
        setUserSession(''/*token*/, data);
        props.history.push('/dashboard');
      }).catch(error => {
        setLoading(false);
        console.log(error);
        setError("Invalid username or password.")
        //if(error.response.status === 401) setError(error.response.data.message);
        //else setError("Something went wrong. Please try again later.")
      });
    }else setError("Illegal characters or empty input fields.")


  }

  const Signup = () => {
    props.history.push("/register")
  }

  return (
    <div>
      Login<br /><br />
      <div>
        Username<br />
        <input type="text" {...username} autoComplete="new-password" />
      </div>
      <div style={{ marginTop: 10 }}>
        Password<br />
        <input type="password" {...password} autoComplete="new-password" />
      </div>
      {error && <><small style={{ color: 'red' }}>{error}</small><br /></>}<br />
      <input type="button" value={loading ? 'Loading...' : 'Login'} onClick={handleLogin} disabled={loading} /><br />
      <br />
      <input type="button" onClick={Signup} value="Need an account?" /><br />

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

export default Login;