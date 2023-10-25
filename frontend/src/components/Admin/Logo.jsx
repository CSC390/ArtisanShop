import React from "react";
import { FaShopify } from "react-icons/fa";
import { Link } from "react-router-dom";

export default function Logo() {
  return (
    <Link to={"/myshop"} className="flex gap-3">
      <FaShopify />
      <span className="">E-commerce Admin</span>
    </Link>
  );
}
