import React from "react";
import ReactDOM from "react-dom/client";
import "bootstrap/dist/css/bootstrap.min.css";
import {
  createBrowserRouter,
  createRoutesFromElements,
  Route,
  RouterProvider,
} from "react-router-dom";
import store from "./utils/store";
import { Provider } from "react-redux";
import App from "./App.jsx";
import Footer from "./components/Footer/Footer";
import PrivateRoute from "./routers/PrivateRoute.jsx";
import HomeScreenProducts from "./components/ProductsHome/HomeScreenProducts";
import ScrollButton from "./components/ScrollToTop/scrollToTop";
import {
  HomeScreen,
  ProfileScreen,
  RegisterScreen,
  LoginScreen,
  ShoppingCart,
  CategoryProduct,
  ProductSinglePage,
  Error,
} from "./screens/index";

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path="/" element={<App />}>
      <Route index={true} path="/" element={<HomeScreen />} />
      <Route path="/login" element={<LoginScreen />} />
      <Route path="/register" element={<RegisterScreen />} />
      <Route path="/cart" element={<ShoppingCart />} />
      {/* single product route */}
      <Route path="/product/:id" element={<ProductSinglePage />} />
      {/* category wise product listing route */}
      <Route path="/category/:category" element={<CategoryProduct />} />
      <Route path="/test" element={<HomeScreenProducts />} />
      <Route path="error" element={<Error />} />
      <Route path="" element={<PrivateRoute />}>
        <Route path="/profile" element={<ProfileScreen />} />
      </Route>
    </Route>
  )
);

ReactDOM.createRoot(document.getElementById("root")).render(
  <Provider store={store}>
    <React.StrictMode>
      <RouterProvider router={router} />
      <ScrollButton />
      <Footer />
    </React.StrictMode>
  </Provider>
);
