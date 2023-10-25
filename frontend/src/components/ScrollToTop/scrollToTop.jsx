import React, { useState, useEffect } from "react";
import { Button } from "react-bootstrap";
import { FaArrowCircleUp } from "react-icons/fa";
import "./scrollToTop.css";

const ScrollButton = () => {
  const [visible, setVisible] = useState(false);

  const toggleVisible = () => {
    const scrolled = document.documentElement.scrollTop;
    if (scrolled > 300) {
      setVisible(true);
    } else {
      setVisible(false);
    }
  };

  const scrollToTop = () => {
    window.scrollTo({
      top: 0,
      behavior: "smooth",
    });
  };

  useEffect(() => {
    window.addEventListener("scroll", toggleVisible);
    return () => {
      window.removeEventListener("scroll", toggleVisible);
    };
  }, []);

  return (
    <Button
      className={`toTop ${visible ? "scrolled" : ""}`}
      onClick={scrollToTop}
      style={{ display: visible ? "inline" : "none" }}
    >
      <FaArrowCircleUp />
    </Button>
  );
};

export default ScrollButton;
