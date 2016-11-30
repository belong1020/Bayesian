package com.bayes.main;

import java.util.Random;

public class BayesaionGS {
	/**  Bayesian�㷨  ����BayesianA��B��C��Cpi������
	 * @param name
	 * @param num
	 * @param trait
	 * @param pi
	 * @param model
	 * @param iteration
	 * @return out_value
	 */
	public static double[] BayesaionGS(String[] name,byte[][] num,
			double[] trait,double pi,String model,int iteration){
		
		double vara=1/20.0;
		double mean2pq=0.5;
		int nua=4;
		int numMHIter=1;
		int nmarkers = num[0].length;	//��������
		int numiter  = iteration;		//ѭ��
		int nrecords = num.length ;
		if(pi==0) pi=1e-100;
		if(pi==1) {System.out.print("Pi should be less then 1 \n");return null;}
		double logPi     = Math.log(pi);
	    double logPiComp = Math.log(1-pi);
	    double varEffects  = vara/(nmarkers*(1-pi)*mean2pq);
		double[] b = new double[nmarkers+1];
		double[] meanb = b;
		double temp = 0;				//temp = trait���
		for(int i=0 ; i< trait.length ; i++){     
	            temp+= trait[i];
	    }temp = temp/trait.length;		//temp = traitƽ��
	    b[0]=temp; 					// b[1] = mean(y);
	    double[] var = new double[nmarkers];
	    double[] ppa = new double[nmarkers];
	    double[] ycorr = new double[trait.length ];
	    for(int i=0 ; i< trait.length  ; i++){ 
	    	ycorr[i]=  trait[i] - b[0];
	    }							// ycorr = y - x%*%b;  ����ת��������  // ԭ������x[,1]Ϊ����   ��ȫ����һ�У���ycorr = y - avg(y) 
	    Random r1 = new Random();
	    
	    if(model.equals("AB")){
	    	System.out.print("Welcoming! Now Bayesian A/B starting...\n");
	        double scaleb  = 0.5*vara/(nmarkers*(1-pi)*mean2pq);
	        double consta  = 2.0*Math.log(scaleb*2.0); 
	        for (int i=0 ; i<numiter ; i++){	//ѭ��  numiter ��
	        	double ycorrResult=0;//   t(ycorr)%*%ycorr
	        	for(int j=0;j<ycorr.length;j++){    //
	        		ycorrResult+=ycorr[j]*ycorr[j];
	        		}
	        	double vare = ( ycorrResult )/Critical.rchisq(1,nrecords+3);   //java�����ֲ�����  -- rchisq
	        	ycorrResult = 0 ;		// �������
	        	
	        	for(int j=0;j<ycorr.length;j++){    
	        		ycorr[j] = ycorr[j] +  b[0];
	        		//ycorr[j] = ycorr[j] + num[0][j] * b[1]; 10-8
	        	}						//ԭ������x[,1]Ϊ����   ��ȫ����һ��
	        	
	        	for(int j=0;j<ycorr.length;j++){    //ycorr��� 
	        		ycorrResult += ycorr[j] ;
	        	}
	        	double rhs    = ycorrResult/vare;
	        	double invLhs = 1.0/(nrecords/vare);
	        	double mean = rhs*invLhs;
	        	b[0] = Math.sqrt(invLhs)*r1.nextGaussian() + mean;
	        	for(int j=0;j<ycorr.length;j++){    
	        		ycorr[j] = ycorr[j] -  b[0]; //��61��ycorr[j] = ycorr[j] - num[j][0] * b[1];
	        	}
	        	meanb[0] = meanb[0] + b[0];	                //sample variance and effect for each locus ���������ÿ��λ���Ӱ��
	        	int nLoci = 0;	        		    		
	        	for (int locus=0 ; locus < nmarkers ; locus++){
					for(int k=0 ;k<ycorr.length ; k++ ){
                            ycorr[k] = ycorr[k] + num[k][locus]*b[locus];
                    }//ycorr = ycorr + x[,1+locus]*b[1+locus];	//��ʽ��������塢��ֵδ��  Ӧ����ɾ������ r����������  ����ȶ�	                	
	        		rhs = 0;
	                for(int j=0;j<ycorr.length;j++){  
	                	rhs += num[j][locus] *ycorr[j] ;
	                }//rhs = t(x[,1+locus])%*%ycorr;
	                double xpx = 0.0 ;
	                for(int j=0;j<num.length;j++){
	                	xpx += num[j][locus]*num[0][locus];
	                }
	                //xpx = t(x[,1+locus])%*%x[,1+locus];	        		
	                double v1  =  (Math.pow(xpx,2)*var[locus] + xpx*vare);  // var[] ��?   �нϴ�����
	                double v2  =  xpx*vare ;
	                double logDataNullModel;
	                //if(v2 == 0.0){
	                //	 logDataNullModel = 0.0;
	                //}else{
	                	 logDataNullModel = -0.5*(Math.log(v2) + rhs*rhs/v2);
	                //}
	                double logDataOld; 
	                double logPriorOld; // ��������
	                double sv;
	                
	                if (var[locus] > 0.0){
	                	//if(v1==0.0){
	                	//	logDataOld=0.0;
	                	//}else{
	                		logDataOld  = -0.5*(Math.log(v1) + Math.pow(rhs,2)/v1);
	                	//}
	                	sv = scaleb;
	                	logPriorOld = consta -3.0*Math.log(var[locus]) - sv*4/(2.0*var[locus]) + logPiComp;
	                	}else{
	                		logDataOld  = logDataNullModel;
	                		logPriorOld = logPi;
	                	}
	                    double logPosteriorOld = logDataOld + logPriorOld;
	                    for (int mhiter = 0; mhiter<numMHIter ; mhiter++){
	                    	double u=(double) Math.random();    //u = runif(1);
	                        Double varCandidate = 0.0 ;   // if Ԥ�� ��  �� 
	                        if (u > 0.5){
	                            if (var[locus] > 0){
	                                varCandidate = var[locus]*2 /Critical.rchisq(1,4);   //�����ֲ�����
	                            } else {
	                                varCandidate = scaleb*4/Critical.rchisq(1,4);    //
	                            } 
	                        }
	                        
	                        double logDataNew;
	                        double logPriorNew;
	                        double logPosteriorNew;
	                        double logProposalOld;
	                        double logProposalNew;
	                        
	                        if (varCandidate > 0.0){
	                            v1  =  (Math.pow(xpx,2)*varCandidate + xpx*vare);
	                            //if(v1 == 0.0){
	                            //	logDataNew = 0.0;
	                            //}else{
	                            	logDataNew =  -0.5*(Math.log(v1) + Math.pow(rhs,2)/v1);
	                            //}
	                            sv = scaleb;
	                            logPriorNew = consta -3.0*Math.log(varCandidate) - sv*4/(2.0*varCandidate) + logPiComp;
	                            logPosteriorNew = logDataNew + logPriorNew;
	                            if(var[locus]>0){
	                                sv = varCandidate*0.5;
	                                logProposalOld = 2.0*Math.log(sv*2.0) -3.0*Math.log(var[locus])   - sv*4/(2.0*var[locus]);
	                                sv = var[locus]*0.5;
	                                logProposalNew = 2.0*Math.log(sv*2.0) -3.0*Math.log(varCandidate) - sv*4/(2.0*varCandidate);
	                            }else{
	                                logProposalOld = 0.0;
	                                sv = scaleb;
	                                logProposalNew = consta -3.0*Math.log(varCandidate) - sv*4/(2.0*varCandidate);	
	                            }
	                            }else{
	                            logDataNew = logDataNullModel;
	                            logPriorNew = logPi;
	                            logPosteriorNew = logDataNew + logPriorNew;
	                            if (var[locus]>0){
	                                sv = scaleb;
	                                logProposalOld = consta -3.0*Math.log(var[locus]) - sv*4/(2.0*var[locus]);
	                                logProposalNew = 0.0;
	                            }else{
	                            logProposalOld = 0.0;
	                            logProposalNew = 0.0;
	                            }
	                        }
	                        double acceptProb = (Math.exp(logPosteriorNew+logProposalOld-logPosteriorOld-logProposalNew));     // Ҳ��r����
	                        u=(double) Math.random();
	                        if( u < acceptProb) {   
	                            var[locus] = varCandidate;
	                            logPosteriorOld = logPosteriorNew;
	                        }
	                    }
	                    if(var[locus]>0) {
	                        nLoci ++;
	                        double lhs = (xpx/vare) + (1.0/var[locus]);	                        
	                        invLhs = 1.0/lhs;
	                        mean = invLhs*rhs/vare;
	                        b[1+locus]= Math.sqrt(invLhs)*r1.nextGaussian() + mean;
	                        for(int k=0 ;k<ycorr.length ; k++ ){
	                            ycorr[k] = ycorr[k] - num[k][locus]*b[1+locus];
	                        }
	                        meanb[1+locus] = meanb[1+locus] + b[1+locus];
	                        ppa[locus] = ppa[locus] + 1;
	                    } else {
	                        b[1+locus] = 0.0;                                 // 1+j->j  184 181  177
	                    }
	        	}
	        }
	    }
	    if (model=="Cpi" || model=="C"){		// C �� Cpi ����
	    	System.out.print("Welcoming! Now Bayes Cpi starting...\n");
	    	double scalec  = varEffects*(nua-2)/nua;	// only used in Bayesian Cpi
	    	double[] storePi = new double[numiter];		// ��¼piֵ
			double piMean  = 0.0;
			double meanVar = 0.0;
			for(int iter=0; iter<numiter; iter++){
				double ycorrResult=0;//   t(ycorr)%*%ycorr
	        	for(int j=0;j<ycorr.length;j++){    //�����ͬ �㷨��ͬ  t() ����ת��
	        		ycorrResult+=ycorr[j]*ycorr[j];
	        		}
	        	double vare = ( ycorrResult )/Critical.rchisq(1,nrecords+3);   //java�����ֲ�����  -- rchisq
	        	ycorrResult = 0 ;		// �������
	        	for(int j=0;j<ycorr.length;j++){    
	        		ycorr[j] = ycorr[j] +  b[0];
	        	}
	        	for(int j=0;j<ycorr.length;j++){    //ycorr��� 
	        		ycorrResult += ycorr[j] ;
	        	}
	        	double rhs    = ycorrResult/vare;
	        	double invLhs = 1.0/(nrecords/vare);
	        	double mean = rhs*invLhs;
	        	b[0] = Math.sqrt(invLhs)*r1.nextGaussian() + mean;//rnorm(10000,2,1) 
	        	for(int j=0;j<ycorr.length;j++){    
	        		ycorr[j] = ycorr[j] -  b[0];			//��206��  ycorr[j] = ycorr[j] - num[j][0] * b[1];
	        	}
	        	meanb[0] = meanb[0] + b[0];	                //sample variance and effect for each locus ���������ÿ��λ���Ӱ��
	        	int nLoci = 0;
	        	for(int locus=0; locus<nmarkers; locus++){
	        		for(int j=0; j<ycorr.length; j++ ){
                        ycorr[j] = ycorr[j] + num[j][locus]*b[1+locus];
                    }
	        			//ycorr = ycorr + x[,1+locus]*b[1+locus];	//��ʽ��������塢��ֵδ��  Ӧ����ɾ������ r����������  ����ȶ�	                	
	                rhs = 0;	//r ��ֵǰ����
	                for(int j=0; j<ycorr.length; j++){  
	                	rhs += num[j][locus] *ycorr[j] ;
	                }//rhs = t(x[,1+locus])%*%ycorr;
	                double xpx = 0.0 ;
	                for(int j=0; j<num.length; j++){
	                	xpx += num[j][locus]*num[j][locus];
	                }
	                double v0  =  xpx*vare;
	                double v1  =  (Math.pow(xpx, 2)*varEffects + xpx*vare);
	                double logDelta0;
	                double logDelta1;
	                //if(v0==0.0){
	                //	logDelta0 = 0.0;
	                //}else{
	                	logDelta0 = -0.5*(Math.log(v0) + Math.pow(rhs, 2)/v0) + logPi; 
	                //}//       
	                //if(v1==0.0){
	                //	logDelta1 = 0.0;
	                //}else{
	                	logDelta1 = -0.5*(Math.log(v1) + Math.pow(rhs, 2)/v1) + logPiComp;
	                //}//
	                double probDelta1 = 1.0/(1.0 + Math.pow(Math.E,logDelta0-logDelta1));
	                double u=(double) Math.random();
	                if(u < probDelta1){
	                	nLoci ++;
                        double lhs = xpx/vare + 1.0/varEffects;	                        
                        invLhs = 1.0/lhs;
                        mean = invLhs*rhs/vare;
                        b[1+locus]= Math.sqrt(invLhs)*r1.nextGaussian() + mean;
                        for(int k=0 ;k<ycorr.length ; k++ ){
                            ycorr[k] = ycorr[k] - num[k][locus]*b[1+locus];
                        }
                        meanb[1+locus] = meanb[1+locus] + b[1+locus];
                        ppa[locus] = ppa[locus] + 1;
                        var[locus] = varEffects;
                    } else {
                        b[1+locus] = 0.0;                     // 
                        var[locus] = 0.0;
                    }
	        	}
	        	//double countLoci = 0;
				double sum = 0.0;
				for(int locus=0; locus<nmarkers; locus++){
					if(var[locus]>0.0){
						//countLoci++;
						sum += Math.pow(b[1+locus],2);
					}
				}
				varEffects = (scalec*nua + sum)/Critical.rchisq(1,nua+nLoci); //countLoci �� nLoci������ͬ��u < probDelta1ʱvar[locus]��>0.0
				meanVar = meanVar + varEffects;
				
				if(model == "Cpi"){//sample pi
					
					double aa =  nmarkers-nLoci + 1;	//countLoci -- nLoci
					double bb =  nLoci + 1;    //countLoci -- nLoci
					//System.out.println(iter+" "+aa+" "+bb);
					pi = Rbeta.rbeta(  aa, bb );
					//System.out.println(iter+" ----- "+pi);
					storePi[iter] = pi; 		//��¼piֵ������������
					piMean = piMean + pi;
					//scalec =  (nua-2)/nua*vara/((1-pi)*nmarkers*mean2pq);   scalec  Ϊ��ֵ��
					logPi     = Math.log(pi);
					logPiComp = Math.log(1-pi);
				}
			}
			piMean = piMean/numiter;
			meanVar = meanVar/numiter;
	   }
	   for(int j=0;j<meanb.length;j++){
	    meanb[j]  = meanb[j] /numiter;
	   }
	   for(int j=0;j<ppa.length;j++){
	    ppa[j]  = ppa[j]/numiter;
	   }
	   double[] effect = new double[meanb.length-1];
	   System.arraycopy(meanb, 1, effect, 0, meanb.length-1);
	   return effect;
	}
}


