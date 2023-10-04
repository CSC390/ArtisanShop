import "../../App.css";
import "../../styles/AdminPage.css";
import { useSelector } from "react-redux";
import Layout from "../../components/Admin/Layout";
import FormContainer from "../../components/FormContainer";

const AdminHome = () => {
  const { userInfo } = useSelector((state) => state.auth);

  return (
    <Layout>
      <div className="text-center">
        <h2>
          Greetings, <b>{userInfo.name}</b>!
        </h2>
        <div className="flex bg-gray-300 gap-1 text-black rounded-lg overflow-hidden">
          {/* <img src={session?.user?.image} alt="" className="w-6 h-6" /> */}
          {/* <span className="px-2">{session?.user?.name}</span> */}
          <FormContainer>
            <p>
              Welcome to the eCommerce Admin Platform, your ultimate solution
              for managing and optimizing your eCommerce business. With our
              intuitive and robust platform, you have the tools you need to
              thrive in the competitive world of online retail.
            </p>
            <br />
            <p>
              🚀 Streamline Your Operations: Manage products, inventory, orders,
              and customer data effortlessly, all in one place. Say goodbye to
              tedious manual tasks.
            </p>
            <br />
            <p>
              🛒 Customize Your Store: Tailor your online store to your brand's
              unique identity.
            </p>
          </FormContainer>
        </div>
      </div>
    </Layout>
  );
};

export default AdminHome;
