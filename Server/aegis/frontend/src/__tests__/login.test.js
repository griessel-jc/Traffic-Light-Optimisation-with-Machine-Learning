import CryptoJS from 'crypto-js';
import { encrypt } from '../utils/Cryptography';
import axios from 'axios';

function login(un,pw){
    const PASSWORD = "This is a password";
    var iv = CryptoJS.lib.WordArray.random(128 / 8).toString(CryptoJS.enc.Hex);
    var salt = CryptoJS.lib.WordArray.random(128 / 8).toString(CryptoJS.enc.Hex);
    var ciphertext = encrypt(128, 1000, salt, iv, PASSWORD, pw);
    var aesPassword = (iv + "::" + salt + "::" + ciphertext);
    //pw = btoa(aesPassword);
    pw = Buffer.from(aesPassword).toString('base64');
    var data = { username: un, password: pw }
    return axios.post('http://localhost:8080/api/login', data);
}


it('testing login with real user',  () => {
   //return login("person1","password1").then(data => expect(data.data.username).not.toBeNull());
   return login("person1","password1").then(data => expect(data.status).toEqual(200));
});

it('testing login with fake user',  () => {
    return login("person1","password2").catch(error => expect(error.response.status).not.toEqual(200));
 });