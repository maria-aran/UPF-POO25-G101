#ifndef __ALGORITHM__
#define __ALGORITHM__

#include "Dataset.h"
#include "Model.h"

class Algorithm {

protected:
    double learningRate;
    double stoppingCriterion;

public:
    Algorithm(double lr, double sc)
        : learningRate(lr), stoppingCriterion(sc) {
    }

    virtual Model solve(Dataset ds) = 0;
};

#endif