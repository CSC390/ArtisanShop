import "./Footer.css";
import {
  FaEnvelope,
  FaFacebook,
  FaGithub,
  FaInstagram,
  FaMapPin,
  FaPhone,
  FaTwitter,
} from "react-icons/fa";
import { Row, Col, Container } from "react-bootstrap";

const Footer = () => {
  return (
    <footer className="footer pt-5">
      <Container className="footer-main">
        <Row>
          <Col lg={4} md={6} xs={12} className="business-hours-col">
            <h3 className="col-title text-center pb-lg-3">
              Support Hotline Hours
            </h3>
            <div className="intro">
              Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean
              commodo ligula.
            </div>
            <ul className="business-hours-list list-unstyled">
              <li>
                <strong>Monday - Friday:</strong> 8am - 6pm
              </li>
              <li>
                <strong>Saturday:</strong> 9am - 4pm
              </li>
              <li>
                <strong>Sunday:</strong> Closed
              </li>
            </ul>
          </Col>

          <Col lg={4} md={6} xs={12} className="contact-us-col">
            <h3 className="col-title text-center pb-lg-3">Contact Us</h3>
            <ul className="footer-contact-list list-unstyled">
              <li className="product">
                <span>
                  <FaPhone />
                </span>
                <span className="info">
                  <a href="tel:01234555666">01234 555 666</a>
                </span>
              </li>
              <li className="product">
                <span>
                  <FaEnvelope />
                </span>
                <span className="info">
                  <a href="mailto:info@yourwebsite.com">info@yourwebsite.com</a>
                </span>
              </li>
            </ul>
          </Col>

          <Col lg={4} xs={12} className="follow-us-col">
            <h3 className="col-title text-center pb-lg-3">About Us</h3>
            <div className="intro">
              Web-based platform designed to connect artisans with potential
              buyers. The goal of this platform is to provide artisans a space
              to showcase and sell their handmade products.
            </div>

            <div className="social-container">
              <ul className="list-inline social-list">
                <li className="list-inline-product social-product">
                  <a href="#http://csc394-blue-frogs-hub.s3-website-us-east-1.amazonaws.com/">
                    <FaInstagram />
                  </a>
                </li>
                <li className="list-inline-product social-product">
                  <a href="http://csc394-blue-frogs-hub.s3-website-us-east-1.amazonaws.com/">
                    <FaGithub />
                  </a>
                </li>
                <li className="list-inline-product social-product">
                  <a href="http://csc394-blue-frogs-hub.s3-website-us-east-1.amazonaws.com/">
                    <FaTwitter />
                  </a>
                </li>
                <li className="list-inline-product social-product">
                  <a href="http://csc394-blue-frogs-hub.s3-website-us-east-1.amazonaws.com/">
                    <FaFacebook />
                  </a>
                </li>
              </ul>
            </div>

            <ul className="footer-menu list-unstyled">
              <li>
                <a href="#">Terms &amp; Conditions</a>
              </li>
              <li>
                <a href="#">Privacy Policy</a>
              </li>
            </ul>
          </Col>
        </Row>
      </Container>
      <div className="bottombar text-center">
        <div className="copyright">
          Company @
          <a
            href="http://csc394-blue-frogs-hub.s3-website-us-east-1.amazonaws.com/"
            rel="noreferrer"
            target="_blank"
          >
            Artisan Marketplace
          </a>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
