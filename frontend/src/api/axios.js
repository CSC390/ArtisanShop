import axios from "axios";

// Axios instance with baseURL for categories
export const axiosCategories = axios.create({
    baseURL: "http://localhost:8080/api/categorys/",
});

// Axios instance with baseURL for products
export const axiosProducts = axios.create({
    baseURL: "http://localhost:8080/api/products/",
});

// Axios instance with baseURL for S3 upload
export const axiosUpload = axios.create({
    baseURL: "http://localhost:8080/api/",
});