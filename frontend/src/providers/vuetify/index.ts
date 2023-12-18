import { createVuetify } from 'vuetify/lib/util/';

import * as components from 'vuetify/components';
import * as directives from 'vuetify/directives';
import { mdi } from 'vuetify/iconsets/mdi';
import 'vuetify/lib/styles/main.sass';
import '@mdi/font/css/materialdesignicons.css';

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
