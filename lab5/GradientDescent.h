#ifndef __GRADIENTDESCENT__
#define __GRADIENTDESCENT__

#include "Algorithm.h"

class GradientDescent : public Algorithm {

public:
    GradientDescent( double lr, double sc )
        : Algorithm(lr, sc) {
    }

    Vector gradient( Dataset ds, Model model ) {
        Vector grad( ds.getDim() + 1, 0 );
        for ( Record r : ds.getData() ) {
            Vector x = r.getInput().augment();
            double error = model.predict(x) - r.getOutput();
            grad = grad.add( x.multiply(error) );
        }
        return grad.divide( (double) ds.getData().size() );
    }

    Model solve( Dataset ds ) override {
        Model model( ds.getDim() + 1 );
        Vector grad( ds.getDim() + 1, 1 );

        while ( grad.norm() > stoppingCriterion ) {
            grad = gradient( ds, model );
            model.update( grad, learningRate );
        }
        return model;
    }
};

#endif
