import axios from "axios";

// Axios instance with baseURL for categories
export const axiosCategories = axios.create({
    baseURL: "http://localhost:8080/api/categorys/",
});

// Axios instance with baseURL for products
export const axiosProducts = axios.create({
    baseURL: "https://dummyjson.com//",
});