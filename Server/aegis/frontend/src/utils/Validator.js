//return whether username and password are of correct length
export const validateCredentials = (username, password) => {
    const userRegex = /[A-Za-z0-9!@#$%]{6,12}/;
    const passRegex = /[A-Za-z0-9!@#$%]{8,20}/;
    return userRegex.test(username.trim()) && passRegex.test(password.trim());
}


//return whether passwords match
export const validatePassword = (password,confPassword) => {
    return password.trim() === confPassword.trim();
}