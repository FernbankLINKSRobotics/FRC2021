/*    
    
 // Working (Tested):   
    
    // 90 Degree Counterclockwise
    
    if (rightfrontencoder.getPosition() < 340 || leftfrontencoder.getPosition() < 340) {
        leftfrontmotor.set(0.1);
        leftrearmotor.set(0.1);
        rightfrontmotor.set(0.1);
        rightrearmotor.set(0.1);
    }
    if (rightfrontencoder.getPosition() > 340 || leftfrontencoder.getPosition() > 340) {
        leftfrontmotor.set(0);
        leftrearmotor.set(0);
        rightfrontmotor.set(0);
        rightrearmotor.set(0);
    }
    
    // 90 Degree Clockwise

    if (rightfrontencoder.getPosition() > -340 || leftfrontencoder.getPosition() > -340) {
        leftfrontmotor.set(-0.1);
        leftrearmotor.set(-0.1);
        rightfrontmotor.set(-0.1);
        rightrearmotor.set(-0.1);
    }
    if (rightfrontencoder.getPosition() < -340 || leftfrontencoder.getPosition() < -340) {
        leftfrontmotor.set(0);
        leftrearmotor.set(0);
        rightfrontmotor.set(0);
        rightrearmotor.set(0);
    }

// Dysfunctional (Nontested):

        
        while ((rightsideDistance() > (leftsideDistance() -100)) || (rightsideDistance() < (leftsideDistance() + 100))) {
            if ((rightsideDistance() > 0) || (leftsideDistance() > 0)){
                double throttle = Constants.DriveBase.Encoders.testingthrottle;
                DriveBase().leftfrontmotor.set(-throttle);
                DriveBase().leftrearmotor.set(-throttle);
    
                DriveBase().rightfrontmotor.set(throttle);
                DriveBase().rightrearmotor.set(throttle);
                
                if ((rightsideDistance() > 720) || (leftsideDistance() > 720)) {
                    double nullthrottle = Constants.DriveBase.Encoders.nullthrottle;
                    DriveBase().leftfrontmotor.set(nullthrottle);
                    DriveBase().leftrearmotor.set(nullthrottle);
        
                    DriveBase().rightfrontmotor.set(nullthrottle);
                    DriveBase().rightrearmotor.set(nullthrottle);

                    isComplete();
                }
            }
        
        
        while (rightsideDistance() < 100 || leftsideDistance() < 100) {

            if ((rightsideDistance() > 0 || rightsideDistance() < 100) || (leftsideDistance() > 0 || leftsideDistance() < 100)) {
                double throttle = Constants.DriveBase.Encoders.testingthrottle;
                DriveBase().leftfrontmotor.set(-throttle);
                DriveBase().leftrearmotor.set(-throttle);

                DriveBase().rightfrontmotor.set(throttle);
                DriveBase().rightrearmotor.set(throttle);
            }

            if ((rightsideDistance() > 100) || (leftsideDistance() > 100)) {
                double nullthrottle = Constants.DriveBase.Encoders.nullthrottle;
                DriveBase().leftfrontmotor.set(nullthrottle);
                DriveBase().leftrearmotor.set(nullthrottle);

                DriveBase().rightfrontmotor.set(nullthrottle);
                DriveBase().rightrearmotor.set(nullthrottle);
        }
    }

     
      while ((rightsideDistance() > 0 || rightsideDistance() < 720) || (leftsideDistance() > 0 || leftsideDistance() < 720)) {
            double throttle = Constants.DriveBase.Encoders.testingthrottle;
            DriveBase().leftfrontmotor.set(-throttle);
            DriveBase().leftrearmotor.set(-throttle);

            DriveBase().rightfrontmotor.set(throttle);
            DriveBase().rightrearmotor.set(throttle);
          
            if ((rightsideDistance() > 720) || (leftsideDistance() > 720)) {
                double nullthrottle = Constants.DriveBase.Encoders.nullthrottle;
                DriveBase().leftfrontmotor.set(nullthrottle);
                DriveBase().leftrearmotor.set(nullthrottle);
    
                DriveBase().rightfrontmotor.set(nullthrottle);
                DriveBase().rightrearmotor.set(nullthrottle);

                isComplete();
            }
        }


            
            if (((rightsideDistance() > 0) || (leftsideDistance() > 0 )) || ((rightsideDistance() > 720) || (leftsideDistance() > 720))) {
            double throttle = Constants.DriveBase.Encoders.nullthrottle;
                DriveBase().leftfrontmotor.set(throttle);
                DriveBase().leftrearmotor.set(throttle);
        
                DriveBase().rightfrontmotor.set(throttle);
                DriveBase().rightrearmotor.set(throttle);
                }     
            




*/