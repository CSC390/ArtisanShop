import "bootstrap/dist/css/bootstrap.min.css";
import Header from "./components/Header/Header";
import { Outlet } from "react-router-dom";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { AllProviders } from "./combineProviders.jsx";

const App = () => {
  return (
    <>
      <AllProviders>
        <Header />
        <ToastContainer />
        <Outlet />
      </AllProviders>
    </>
  );
};

export default App;
