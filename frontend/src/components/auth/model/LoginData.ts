class LoginData {
    email = '';

    password = '';

    isContainsValues = () => !!this.email && !!this.password;
}

export default LoginData;