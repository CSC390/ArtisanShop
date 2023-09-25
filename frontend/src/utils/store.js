import { configureStore } from "@reduxjs/toolkit"
import authReducer from '../slices/authSlice'
import { apiSlice } from "../slices/apiSlice";
import productReducer from "../slices/productSlice";
import categoryReducer from "../slices/categorySlice";
import cartReducer from "../slices/cartSlice";
import sidebarReducer from "../slices/sidebarSlice";


const store = configureStore({
    reducer: {
        auth: authReducer,
        [apiSlice.reducerPath]: apiSlice.reducer,
        sidebar: sidebarReducer,
        category: categoryReducer,
        product: productReducer,
        cart: cartReducer

    },
    middleware: (getDefaultMiddlerware) => getDefaultMiddlerware().concat(apiSlice.middleware),
    devTools: true
});

export default store;