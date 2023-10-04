import React, { useState } from "react";
import Nav from "../../components/Admin/Nav";

export default function Layout({ children }) {
  const [showNav, setShowNav] = useState(false);
  // const { data: session } = useSession();

  // if (!session) {
  //   return (
  //     <div className="bg-lightGray h-screen d-flex align-items-center justify-content-center">
  //       <div className="text-center w-100">
  //         <button onClick={() => signIn("google")} className="btn btn-primary">
  //           Login with Google
  //         </button>
  //       </div>
  //     </div>
  //   );

  return (
    <div className="bg-lightGray min-vh-100">
      <div className="d-block d-md-none d-flex align-items-center">
        <div className="d-flex justify-content-center mr-6"></div>
      </div>
      <div className="d-flex min-vh-100">
        <Nav show={showNav} />
        <div className="flex-grow-1 p-4">{children}</div>
      </div>
    </div>
  );
}
