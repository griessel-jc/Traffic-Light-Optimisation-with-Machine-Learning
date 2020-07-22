import CryptoJS from 'crypto-js';
import {encrypt} from '../utils/Cryptography';

test('encrypting text', () => {
    const PASSWORD  = "This is a password";
    const iv        = CryptoJS.lib.WordArray.random(128 / 8).toString(CryptoJS.enc.Hex);
    const salt      = CryptoJS.lib.WordArray.random(128 / 8).toString(CryptoJS.enc.Hex);
    expect(encrypt(128, 1000, salt, iv, PASSWORD, "user")).toBeDefined();
});
