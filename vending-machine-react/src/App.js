import React from 'react';
import './App.css';
import { Form, Button } from 'react-bootstrap'

import { Container, Row, Col } from 'react-bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css';

const SERVICE_URL = "http://vending.us-east-1.elasticbeanstalk.com/"


const productButtonStyle = {
  border: "1px solid blue",
  width: "30%",
  height: "200px",
  margin: "5px",
  padding: "3px",
  textAlign: "center",
  display: "inline-block"
}

const walletButtonStyle = {
  width: "45%",
  margin: "5px",
  display: "inline-block"
}

class App extends React.Component{

  state = {
    loading: false,
    productData: [
      {
        "id": 2,
        "name": "Still Fake",
        "price": 2.00,
        "quantity": 1
      }],
      wallet: 0.00,
      itemId: 1,
      coins:{
        "quarters": 0,
        "dimes": 0,
        "nickels": 0,
        "pennies": 0
      },
      someMessage: "No Problems",
      totalChange: 0.00,
      coinString: ""
  }

  addDollar(){
    let x = this.state.wallet + 1.00;
    this.setState({wallet : x});
  }
  addQuarter(){
    let x = this.state.wallet + 0.25;
    this.setState({wallet : x});
  }
  addDime(){
    let x = this.state.wallet + 0.10;
    this.setState({wallet : x});
  }
  addNickel(){
    let x = this.state.wallet + 0.05;
    this.setState({wallet : x});
  }      

  itemSelector(i){
    // alert("Test");
    this.setState({itemId : i});
  }

  purchaseCalculations(coin){
    this.setState({ coins: coin})
    this.componentDidMount();
    this.setState({someMessage: "Thank You!!!"});
    this.setState({wallet: 0.00});
    // this.calculateTotalChange();
  }
  
  calculateTotalChange(coin){
    let qval = coin.quarters*0.25;
    let dval = coin.dimes*0.10;
    let nval = coin.nickels*0.05;
    let pval = coin.pennies*0.01;
    let total = qval+dval+nval+pval;

    this.setState({totalChange : total});
    this.coinMessage(coin.quarters, coin.dimes, coin.nickels, coin.pennies);
  }

  coinMessage(q, d, n ,p){
    let string = "";
    if (q == 1){
      string = string + "1 Quarter";
    }
    else if (q > 1){
      string = string + q + " Quarters";
    }

    if(q != 0 && d != 0){
      string = string + ", ";     
    }
    if (d == 1){
      string = string + "1 Dime";
    }
    else if (d > 1){
      string = string + d + " Dimes";
    } 

    if((q != 0 || d != 0) && n != 0){
      string = string + ", ";
    }
    if (n == 1){
      string = string + "1 Nickel";
    }
    else if (n > 1){
      string = string + n + " Nickels";
    }

    if((q != 0 || d != 0 || n != 0) && p != 0){
      string = string + ", ";
    }
    if (p == 1){
      string = string + "1 Penny";
    }
    else if (p > 1){
      string = string + p + " Pennies";
    }  
    // alert(string);
    this.setState({coinString: string});
 
  }

  componentDidMount() {
    console.log("App is now mounted.")
    this.setState({ loading: true })
    console.log("Loading contact data")
    fetch(SERVICE_URL + "/items")
      .then(data => data.json())
      .then(data => this.setState(
        { productData: data, loading: false }
      ))
  }  

  purchaseItem = (event) => {
    // alert("Purchasing Item!");
    if (event) event.preventDefault();

    fetch(SERVICE_URL + '/money/'+this.state.wallet.toFixed(2)+'/item/'+this.state.itemId, {
      method: 'POST'
    })
      .then(response => response.json())
      .then(response => {
        // alert("Success")
        console.log('Purchase - Success:', response);
        if(response.message){
          // alert(response.message);
          this.setState({someMessage: response.message});
        }
        else{
          this.purchaseCalculations(response);
          // this.setState({ coins: response})
          // this.componentDidMount();
          // this.setState({someMessage: "Thank You!!!"});
          this.calculateTotalChange(response);          
        }
      })
      .catch((error) => {
        this.setState({someMessage: error.message});
        console.log('Add Contact - Error:');
        alert("There Was an error");
      });
  }

  
  render(){
      return (
        <Container>
        <Row>
          <Col>
            <h1 className="text-center">Vending Machine</h1>
          </Col>
        </Row>
        <hr />
        <Row>
          <Container style={{width:"66%"}}>
            <h2>Items</h2>
            <tbody>
            {this.state.productData.slice(0,9).map((product, index) => {
                 return <div onClick={() => this.itemSelector(product.id)} style={productButtonStyle}>  
                      <p style={{textAlign:"left", margin:"0px"}}>{product.id}</p>
                      {product.name}<br></br>
                      ${product.price.toFixed(2)}<br></br>
                      Quantity Left: {product.quantity}
                  </div>
            })}
            </tbody>            
          </Container>

          <Col>

              <Row>
                <h2>Total $ In</h2>   
                <Form>
                  <Form.Group controlId="walletCount">
                      <Form.Control type="text" placeholder={(this.state.wallet).toFixed(2)} readOnly/>
                  </Form.Group>
                  <div style={walletButtonStyle}> 
                      <Button onClick={() => this.addDollar()}>
                      Add Dollar
                      </Button>
                  </div>
                  <div style={walletButtonStyle}> 
                      <Button onClick={() => this.addQuarter()}>
                          Add Quarter
                      </Button>
                  </div>
                  <div style={walletButtonStyle}> 
                      <Button onClick={() => this.addDime()}>
                          Add Dime
                      </Button>
                  </div>
                  <div style={walletButtonStyle}> 
                      <Button onClick={() => this.addNickel()}>
                          Add Nickel
                      </Button>
                  </div>                                                                
                </Form>                   
              </Row>

              <Row>
                <h2>Messages</h2>
                <Form.Group controlId="vendingMessage">
                    <Form.Control type="text" placeholder={this.state.someMessage} readOnly/>
                </Form.Group>
                <Form.Group controlId="itemSelected">
                    <label>Item:</label>
                    <Form.Control type="text" placeholder={this.state.itemId} readOnly/>
                </Form.Group>  
                </Row>
                <br/>
                <center>
                <Button onClick={() => this.purchaseItem()}>
                     Make Purchase
                </Button>     
                </center>           

              <Row>
                <h2>Change</h2>
                  Total Change: {(this.state.totalChange).toFixed(2)}<br/>
                  {this.state.coinString}<br/>
              </Row>  

          </Col>
        </Row>
      </Container>
    );
  }
}
export default App;