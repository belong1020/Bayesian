package com.bayes.Cross;

import com.bayes.domain.Bayesian;

public interface Group {

	void group(Bayesian bayes,int k,int x);
	
	void trainCV(Bayesian bayes , int k,int x);
	void testCV(Bayesian bayes , int k,int x);
	
	void trainCV(Bayesian bayes , int k , int x , boolean bool);
	void testCV(Bayesian bayes , int k , int x , boolean bool);
	
}
