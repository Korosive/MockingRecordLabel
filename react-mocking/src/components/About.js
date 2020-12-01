import React, {Fragment} from 'react';

function About() {
	return(
		<Fragment>
			<h1 className="display-1 p-5 m-0 text-center bg-dark">About The 'Mocking' Record Label</h1>
			<p className="text-center p-5 lead bg-dark">Founded by Jay ... </p>
			<h1 className="lead">Contact Us Through Email <a href="mailto:contactus@mockingrecords.com">Here</a></h1>
			<div className="social-media">
				<h1 className="lead">Connect with us on the following:</h1>
			</div>
		</Fragment>
	);
}

export default About;