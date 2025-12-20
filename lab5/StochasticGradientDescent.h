#ifndef __STOCHASTICGRADIENTDESCENT__
#define __STOCHASTICGRADIENTDESCENT__

#include "Algorithm.h"

class StochasticGradientDescent : public Algorithm {

private:
    int batchSize;
    int iterations;

public:
    StochasticGradientDescent( double lr, int bs, int it )
        : Algorithm(lr, 0), batchSize(bs), iterations(it) {
    }

    Vector stochasticGradient( std::vector<Record> batch, Model model ) {
        Vector grad( model.predict(batch[0].getInput().augment()), 0 );
        grad = Vector( batch[0].getInput().augment().getDim(), 0 );

        for ( Record r : batch ) {
            Vector x = r.getInput().augment();
            double error = model.predict(x) - r.getOutput();
            grad = grad.add( x.multiply(error) );
        }
        return grad.divide( (double) batch.size() );
    }

    Model solve( Dataset ds ) override {
        Model model( ds.getDim() + 1 );
        auto data = ds.getData();

        for ( int i = 0; i < iterations; ++i ) {
            std::vector<Record> batch;
            for ( int i = 0; i < batchSize; ++i ) {
                int idx = rand() % data.size();
                batch.push_back( data[idx] );
            }

            Vector grad = stochasticGradient( batch, model );
            model.update( grad, learningRate );
        }
        return model;
    }
};

#endif
