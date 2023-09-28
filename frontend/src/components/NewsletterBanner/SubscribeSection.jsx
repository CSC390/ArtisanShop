import { Container, Row, Col, Form, Button } from "react-bootstrap";

const SubscribeSection = () => {
  return (
    <Container className="bg-secondary my-5">
      <Row className="justify-content-md-center py-5 px-xl-5">
        <Col md={6} xs={12} py={5}>
          <div className="text-center mb-2 pb-2">
            <h2 className="section-title px-5 mb-3">
              <span className="bg-secondary px-2">Stay Updated</span>
            </h2>
            <p>
              Stay in the loop with the latest updates, exclusive offers, and
              exciting promotions from our shop. Join our newsletter to receive
              the best deals delivered right to your inbox.
            </p>
          </div>
          <Form>
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
