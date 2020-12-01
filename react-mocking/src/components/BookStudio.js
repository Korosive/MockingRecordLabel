import React, {Component, Fragment} from 'react';
import axios from 'axios';

class BookStudio extends Component {
	constructor() {
		super()
		this.state = {startDate: '', endDate: '', contactEmail: '', description: ''};
	}

	handleBook = (e) => {
		e.preventDefault();
		const start_date = this.state.startDate;
		const end_date = this.state.endDate;
		const description = this.state.description;
		axios.post("/api/book/add", {
			name: "STUDIO",
			start_date: start_date,
			end_date: end_date,
			description: description
		}).then(response => {
			const data = response.data;
			if (data.success) {
				this.setState({success: data.message});
			} else {
				this.setState({failed: data.message});
			}
		}).catch(error => {
			this.setState({error: error.message});
		})
	}

	handleReset = (e) => {
		e.preventDefault();
		this.setState({
			startDate: '',
			endDate: '',
			contactEmail: '',
			description: ''
		});
	}

	handleStartDateChange = (e) => {
		e.preventDefault();
		this.setState({startDate: e.target.value});
	}

	handleEndDateChange = (e) => {
		e.preventDefault();
		this.setState({endDate: e.target.value});
	}

	handleEmailChange = (e) => {
		e.preventDefault();
		this.setState({contactEmail: e.target.value});
	}

	handleDescriptionChange = (e) => {
		e.preventDefault();
		this.setState({description: e.target.value});
	}

	render() {
		var strSuccess;
		if (this.state.success) {
			strSuccess = <div className="alert alert-success mx-2" role="alert">
				{this.state.success}
			</div>
		} else if(this.state.error) {
			strSuccess = <div className="alert alert-danger" role="alert">
				{this.state.error}
			</div>
		} else if(this.state.failed) {
			strSuccess = <div className="alert alert-danger" role="alert">
				{this.state.failed}
			</div>
		}

		return(
			<Fragment>
				<h1 className="display-1 text-center p-5 bg-dark m-0 mt-5">Book Studio</h1>
				{strSuccess}
				<form className="m-0 p-5 bg-dark">
					<div className="form-group">
						<label htmlFor="startDate">Start Date: </label>
						<input type="date"
							className="form-control"
							id="startDate"
							value={this.state.startDate}
							onChange={this.handleStartDateChange}
						/>
						<label htmlFor="endDate">End Date: </label>
						<input type="date"
							className="form-control"
							id="endDate"
							min={this.state.startDate}
							value={this.state.endDate}
							onChange={this.handleEndDateChange}
						/>
					</div>
					<div className="form-group">
						<label htmlFor="contactEmail">Contact Email: </label>
						<input type="email"
							className="form-control"
							id="contactEmail"
							value={this.state.contactEmail}
							onChange={this.handleEmailChange}
						/>
					</div>
					<div className="form-group">
						<label htmlFor="description">Session Description: </label>
						<textarea id="description"
							className="form-control"
							value={this.state.description}
							onChange={this.handleDescriptionChange}
						/>
						<small>Please be descriptive as possible</small>
					</div>
					<div className="form-group">
						<input type="submit"
							className="btn btn-primary m-3"
							value="Book Session"
							id="btn book"
							onClick={this.handleBook}
						/>
						<input type="submit"
							className="btn btn-secondary m-3"
							id="btn reset"
							value="Reset"
							onClick={this.handleReset}
						/>
						<input type="submit"
							className="btn btn-secondary m-3"
							id= "btn cancel"
							value="Cancel"
							onClick={this.handleCancel}
						/>
					</div>
				</form>
			</Fragment>
		);
	}
}

export default BookStudio;