import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { STATUS } from "../utils/status";
const BASE_URL = '/api/';

const initialState = {
    categories: [],
    categoriesStatus: STATUS.IDLE,
    categoryProducts: [],
    categoryProductsStatus: STATUS.IDLE
}

const categorySlice = createSlice({
    name: 'category',
    initialState,
    reducers: {},
    extraReducers: (builder) => {
        builder
            .addCase(fetchAsyncCategories.pending, (state, action) => {
                state.categoriesStatus = STATUS.LOADING;
            })

            .addCase(fetchAsyncCategories.fulfilled, (state, action) => {
                state.categories = action.payload;
                state.categoriesStatus = STATUS.SUCCEEDED;
            })

            .addCase(fetchAsyncCategories.rejected, (state, action) => {
                state.categoriesStatus = STATUS.FAILED;
            })

            .addCase(fetchAsyncProductsOfCategory.pending, (state, action) => {
                state.categoryProductsStatus = STATUS.LOADING;
            })

            .addCase(fetchAsyncProductsOfCategory.fulfilled, (state, action) => {
                state.categoryProducts = action.payload;
                state.categoryProductsStatus = STATUS.SUCCEEDED;
            })

            .addCase(fetchAsyncProductsOfCategory.rejected, (state, action) => {
                state.categoryProductsStatus = STATUS.FAILED;
            })
    }
});

// Return the categories in the DB.
export const fetchAsyncCategories = createAsyncThunk('categories/fetch', async () => {
    const response = await fetch(`${BASE_URL}categorys/getCategories`);
    const data = await response.json();

    // Limit the result to, for example, the first 5 categories
    // const limitedData = data.slice(0, 12); // Adjust the range as needed

    return data;
});

// Return products of a category.
export const fetchAsyncProductsOfCategory = createAsyncThunk('category-products/fetch', async (category) => {
    const response = await fetch(`${BASE_URL}products/category/${category}`);
    const data = await response.json();
    return data.products;
});

export const getAllCategories = (state) => state.category.categories;
export const getAllProductsByCategory = (state) => state.category.categoryProducts;
export const getCategoryProductsStatus = (state) => state.category.categoryProductsStatus;
export default categorySlice.reducer;