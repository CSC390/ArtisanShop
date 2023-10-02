import { Container, Row, Col, Form, Button } from "react-bootstrap";

const SubscribeSection = () => {
  return (
    <Container className=" my-5 border bg-white">
      <Row className="justify-content-md-center py-4 ">
        <Col md={6} xs={12} py={5}>
          <div className="text-center mb-2 pb-2">
            <h2 className="section-title px-5 mb-4">
              <span className="px-2">Stay Updated</span>
            </h2>
            <p>
              Stay in the loop with the latest updates, exclusive offers, and
              exciting promotions from our shop. Join our newsletter to receive
              the best deals delivered right to your inbox.
            </p>
          </div>
          <Form className="text-center">
            <Form.Group className="my-2" controlId="email">
              <Form.Label>Email Address</Form.Label>
              <Form.Control type="email" placeholder="Enter Email" />
            </Form.Group>
            <Button variant="primary" type="submit" className="mt-2">
              Submit
            </Button>
          </Form>
        </Col>
      </Row>
    </Container>
  );
};

export default SubscribeSection;
