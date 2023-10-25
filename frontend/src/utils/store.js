import { configureStore } from "@reduxjs/toolkit"
import authReducer from '../reducers/authReducer'
import { apiSlice } from "../reducers/apiSlice";
import productReducer from "../reducers/productReducer";
import categoryReducer from "../reducers/categorySlice";
import basketReducer from "../reducers/basketReducer";
import sidebarReducer from "../reducers/sidebarSlice";


const store = configureStore({
    reducer: {
        auth: authReducer,
        [apiSlice.reducerPath]: apiSlice.reducer,
        sidebar: sidebarReducer,
        category: categoryReducer,
    },
    middleware: (getDefaultMiddlerware) => getDefaultMiddlerware().concat(apiSlice.middleware),
    devTools: true
});

export default store;