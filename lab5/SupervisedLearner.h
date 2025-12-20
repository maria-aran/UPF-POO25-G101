#ifndef __SUPERVISEDLEARNER__
#define __SUPERVISEDLEARNER__

#include "Algorithm.h"

class SupervisedLearner {

private:
    Algorithm * algorithm;
    Dataset dataset;
    Model model;

public:
    SupervisedLearner( Algorithm * a, Dataset d )
        : algorithm(a), dataset(d), model(d.getDim() + 1) {
    }

    void solve() {
        model = algorithm->solve( dataset );
    }

    double predict( const Vector & v ) {
        return model.predict( v.augment() );
    }

    friend std::ostream & operator<<( std::ostream & os, SupervisedLearner & sl ) {
        return os << sl.model;
    }
};

#endif
