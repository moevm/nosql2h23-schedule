type validationOptionsBase = {
    message: string;
};

const useRequiredValidation =
    (options: validationOptionsBase) => (v: unknown) =>
        !!v || options.message;

export const useMinLengthStringValidation = (
    options: validationOptionsBase & { minLength: number },
) => (value: string) => value.length >= options.minLength || options.message;

const loginValidationRules = {
    email: [
        useRequiredValidation({ message: 'Логин - обязательное поле' }),
        useMinLengthStringValidation({
            message: 'Логин должен быть длиннее 6 символов',
            minLength: 6,
        }),
    ],
    password: [useRequiredValidation({ message: 'Пароль - обязательное поле' })],
};

export default loginValidationRules;