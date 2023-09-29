import React from "react";
import ReactDOM from "react-dom/client";
import "bootstrap/dist/css/bootstrap.min.css";
import {
  createBrowserRouter,
  createRoutesFromElements,
  Route,
  RouterProvider,
} from "react-router-dom";
import App from "./App.jsx";
import store from "../src/utils/store.js";
import { Provider } from "react-redux";
import Footer from "./components/Footer/Footer";
import PrivateRoute from "./routers/PrivateRoute.jsx";
import ScrollButton from "./components/ScrollToTop/scrollToTop";
import {
  HomeScreen,
  ProfileScreen,
  RegisterScreen,
  LoginScreen,
  Error,
  ViewCategoryProductList,
  ViewProductSingle,
} from "./screens/index";

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path="/" element={<App />}>
      <Route index={true} path="/" element={<HomeScreen />} />
      <Route path="/login" element={<LoginScreen />} />
      <Route path="/register" element={<RegisterScreen />} />
      <Route
        path="category/:categoryKey"
        element={<ViewCategoryProductList />}
      />
      <Route path="products/:id" element={<ViewProductSingle />} />
      <Route path="error" element={<Error />} />
      <Route path="" element={<PrivateRoute />}>
        <Route path="/profile" element={<ProfileScreen />} />
      </Route>
    </Route>
  )
);

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <Provider store={store}>
      <RouterProvider router={router} />
      <ScrollButton />
      <Footer />
    </Provider>
  </React.StrictMode>
);
