import React from "react";
 
const SideButtonsAndStuff = props => {
  return (
    <div className="container-fluid"> 
		<h3 id="sitePageTitle">
			Vending Machine
			<hr />
		</h3>
			<div className="container-fluid">
				<div className="row">
					<div className="col-md-12">
						<div className="row">
							<div className="col-md-9">
								<div className="row">
								<div id="allItemsOnLoadUp" className="row">

							</div>
          				</div>
          			</div>

          			<div className="col-md-3 theStaticContent">
						<form id="totalInForm">
          					<div className="row">
          						<div className="col-md-12 ">
									<div className="col-md-2">
										<h4 id="totalInText" className="textLabels">
											Total$
										</h4>
									</div>
          						</div>
          					</div>
							
          					<div className="row">
          						<div className="col-md-12 theBoxDivs">
									<input id="moneyBox" className="theBoxes" type="text" value="0.00" readOnly />
									<input id="hiddenMoney" type="hidden" value="0.00" />
								</div>
          					</div>
							<br />
          					<div className="row moneyButtonRow">
          						<div className="col-md-6">
									<button type="button" id ="dollar" className="btn btn-success theFour">
										Add Dollar
									</button>
          						</div>
          						<div className="col-md-6 quarNick">
									<button type="button"  id ="quarter" className="btn btn-success theFour">
									  Add Quarter
									</button>
          						</div>
          					</div>

          					<div className="row moneyButtonRow">
          						<div className="col-md-6 moneyButtonPadding">
									<button type="button"  id ="dime" className="btn btn-success theFour">
									  Add Dime
									</button>
          						</div>
          						<div className="col-md-6 quarNick moneyButtonPadding">
									<button type="button"  id ="nickel" className="btn btn-success theFour">
									  Add Nickel
									</button>
          						</div>
          					</div>
						</form>
						<hr className="horizontalRules"/>

						<form id="messagesForm">
							<div className="row">
								<div className="col-md-12">
									<h4 className="textLabels">Messages</h4>
								</div>
							</div>
							<div className="row">
								<div className="col-md-12 theBoxDivs">
									<input id="messageBox" className="theBoxes" type="text" readOnly/>
								</div>
							</div>

							<div className="row">
								<div className="col-md-4 itemBoxPadding">
									<h4 id="itemText" className="textLabels">
										Item:
									</h4>

								</div>

								<div className="col-md-8 theBoxDivs itemBoxPadding">

								  <input id="itemBox" className="theBoxes" type="text" readOnly/>
								  <input type="hidden" id="hiddenItemId"/>

								</div>
							</div>

							<div className="row">
								<div className="col-md-12">
									<div className="container columnButtons purChangePadding">
										<button type="button" id="makePurchaseButton" className="btn btn-success">
											Make Purchase
										</button>
									</div>
								</div>
							</div>
						</form>
						
						<hr className="horizontalRules"/>
						<div className="row">
							<div className="col-md-12">
								<h4 className="textLabels">Change</h4>
							</div>
						</div>

						<div className="row">
							<div className="col-md-12 theBoxDivs">
								<input type="text" id="changeBox" className="theBoxes" readOnly />
							</div>
						</div>

						<div className="row">
							<div id = "changeReturnButton" className="col-md-12">
								<div id="changeButtonPadding" className="container columnButtons">
									<button type="button" className="btn btn-success">
										Change Return
									</button>
									</div>
								</div>
							</div>
          				</div>
          			</div>
          		</div>
			</div>
        </div>
    </div>

	  );
};
 
export default SideButtonsAndStuff;