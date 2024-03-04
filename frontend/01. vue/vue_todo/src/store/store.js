import Vuex from 'vuex'
import todoApp from './modules/todoApp.js'

export const store = new Vuex.Store({
    modules: {
        todoApp
    }
});