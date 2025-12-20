#include "Dataset.h"
#include "Model.h"
#include "GradientDescent.h"
#include "SupervisedLearner.h"
#include "StochasticGradientDescent.h"
#include "Record.h"
#include "Algorithm.h"
#include <iostream>

int main() {

    Vector v1( std::vector<double> { 0.5, 1.5, 2.5 } );
    Vector v2( std::vector<double> { 8.0, 6.0, 4.0 } );
    Vector v3( std::vector<double> { 3.6, 4.8, 7.2 } );
    
    std::cout << v1 << "\n" << v2 << "\n" << v3 << "\n";
    
    Vector v4 = v1.add( v3 );
    Vector v5 = v2.subtract( v1 );
    Vector v6 = v1.multiply( 4 );
    Vector v7 = v3.divide( 1.2 );
    Vector v8 = v2.multiply( v2 );
    
    std::cout << v4 << "\n" << v5 << "\n" << v6 << "\n" << v7 << "\n" << v8 << "\n";
    
    double d1 = v1.dotProduct( v2 );
    double d2 = v2.norm();
    
    std::cout << d1 << " " << d2 << "\n";

    std::cout << "\nTesting linear regression test\n";

    Dataset ds(2);

    ds.addRecord( Record( Vector( std::vector<double>{1, 1}), 2 ) );
    ds.addRecord( Record( Vector( std::vector<double>{2, 1}), 3 ) );
    ds.addRecord( Record( Vector( std::vector<double>{3, 1} ), 4 ) );
    ds.addRecord( Record( Vector( std::vector<double>{4, 1} ), 5 ) );

    GradientDescent gd( 0.01, 0.001 );
    SupervisedLearner learner1( &gd, ds );
    learner1.solve();

    Vector test( std::vector<double>{5, 1} );
    std::cout << "GD prediction for [5,1]: " << learner1.predict(test) << "\n";

    StochasticGradientDescent sgd( 0.01, 2, 1000 );
    SupervisedLearner learner2( &sgd, ds );
    learner2.solve();

    std::cout << "SGD prediction for [5,1]: " << learner2.predict(test) << "\n";

    return 0;
}