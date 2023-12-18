import * as components from 'vuetify/components';
import * as directives from 'vuetify/directives';
import { mdi } from 'vuetify/iconsets/mdi';
import 'vuetify/styles'
import 'vuetify/lib/styles/main.sass';
import '@mdi/font/css/materialdesignicons.css';
import { createVuetify } from 'vuetify';

const vuetify = createVuetify({
    icons: {
        defaultSet: 'mdi',
        sets: {
            mdi,
        },
    },
    components,
    directives,
});

export default vuetify;
