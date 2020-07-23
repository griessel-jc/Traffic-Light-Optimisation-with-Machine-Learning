import CryptoJS from 'crypto-js';

export const encrypt = (keySize_param, interationCount_param, salt,iv,passPhrase, plainText) =>{
    
    let keySize           = keySize_param/32;
    let iterationCount    = interationCount_param;
    function generateKey(salt,passPhrase){
        var key = CryptoJS.PBKDF2(
            passPhrase,
            CryptoJS.enc.Hex.parse(salt),
            { keySize:  keySize, iterations: iterationCount });
        return key;
    }

    var key = generateKey(salt, passPhrase);
    var encrypted = CryptoJS.AES.encrypt(
        plainText,
        key,
        { iv: CryptoJS.enc.Hex.parse(iv) });
    return encrypted.ciphertext.toString(CryptoJS.enc.Base64);
}