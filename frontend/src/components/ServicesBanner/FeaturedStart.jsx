import { Col, Container, Row } from "react-bootstrap";
import {
  FaShippingFast,
  FaCheck,
  FaExchangeAlt,
  FaPhoneVolume,
} from "react-icons/fa";

const FeaturedStart = () => {
  return (
    <Container fluid className="pt-5">
      <Row className="px-xl-5 pb-3">
        <Col lg={3} md={6} sm={12} className="pb-1">
          <div className="d-flex align-items-center border mb-4 p-3">
            <FaCheck /> {/* Use the FaCheck component */}
            <h5 className="font-weight-semi-bold m-0">Quality Product</h5>
          </div>
        </Col>
        <Col lg={3} md={6} sm={12} className="pb-1">
          <div className="d-flex align-items-center border mb-4 p-3">
            <FaShippingFast />
            <h5 className="font-weight-semi-bold m-0"> Free Shipping</h5>
          </div>
        </Col>
        <Col lg={3} md={6} sm={12} className="pb-1">
          <div className="d-flex align-items-center border mb-4 p-3">
            <FaExchangeAlt />
            <h5 className="font-weight-semi-bold m-0"> 14-Day Return</h5>
          </div>
        </Col>
        <Col lg={3} md={6} sm={12} className="pb-1">
          <div className="d-flex align-items-center border mb-4 p-3">
            <FaPhoneVolume />
            <h5 className="font-weight-semi-bold m-0"> 24/7 Support</h5>
          </div>
        </Col>
      </Row>
    </Container>
  );
};

export default FeaturedStart;
