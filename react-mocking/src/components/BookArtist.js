import React, {Component, Fragment} from 'react';
import axios from 'axios';
import {Redirect} from 'react-router-dom';

class BookArtist extends Component {
	constructor() {
		super()
		this.state = {artist_id: '', image: '', name: '', startDate: '', endDate: '', contactEmail: '', description: ''};
	}

	componentDidMount() {
		const artist_id = this.props.match.params.id;
		if (artist_id) {
			axios.get("/api/artist/get/" + artist_id)
			.then(response => {
				const data = response.data;
				this.setState({
					id: artist_id,
					image: data.image,
					name: data.name
				});
			}).catch(error => {
				this.setState({status: error.message});
			});
		}
	}

	handleStartDateChange = (e) => {
		e.preventDefault();
		this.setState({startDate: e.target.value});
	}
	handleEndDateChange = (e) => {
		e.preventDefault();
		this.setState({endDate: e.target.value});
	}
	handleContactEmailChange = (e) => {
		e.preventDefault();
		this.setState({contactEmail: e.target.value});
	}
	handleDescriptionChange = (e) => {
		e.preventDefault();
		this.setState({description: e.target.value})
	}

	handleBook = (e) => {
		e.preventDefault();
		const start_date = this.state.startDate;
		const end_date = this.state.endDate;
		const description = this.state.description;
		const name = this.state.name;
		axios.post("/api/book/add", {
			name: name,
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
		});
	}

	handleReset = (e) => {
		e.preventDefault();
		this.setState({
			startDate: '',
			endDate: '',
			description: '',
			status: "Reset input."
		});
	}

	handleCancel = (e) => {
		e.preventDefault();
		this.setState({cancel: true});
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

		if (this.state.cancel) {
			return(<Redirect to="/" />);
		}

		return(
			<Fragment>
				<h1 className="display-1 text-center bg-dark p-5">Book {this.state.name}</h1>
				{strSuccess}
				<div className="media m-5">
					<img src={"data:image/png;base64," + this.state.image} alt={this.state.name} style={{width:350}}/>
					<div className="media-body mx-5">
						<form>
							<div className="form-group">
								<label htmlFor="startDate">Start Date: </label>
								<input type="date"
									className="form-control"
									id="startDate"
									value={this.state.startDate}
									onChange={this.handleStartDateChange}
								/>
							</div>
							<div className="form-group">
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
									onChange={this.handleContactEmailChange}
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
							<input type="submit"
								value="Book Session"
								className="btn btn-primary mx-2"
								onClick={this.handleBook}
							/>
							<input type="submit"
								value="Reset"
								className="btn btn-primary mx-2"
								onClick={this.handleReset}
							/>
							<input type="submit"
								value="Cancel"
								className="btn btn-primary mx-2"
								onClick={this.handleCancel}
							/>
						</form>
					</div>
				</div>
			</Fragment>
		);
	}
}

export default BookArtist;