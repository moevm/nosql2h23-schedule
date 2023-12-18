class LoginData {
    username = '';

    password = '';

    isContainsValues = () => !!this.username && !!this.password;
}

export default LoginData;