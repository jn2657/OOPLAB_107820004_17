#include "stdafx.h"
#include <string>
#include <iostream>

using namespace System;

bool Guess(int number){
	return false;
}

int main(array<System::String ^> ^args){
	int guess;
	int guess1;
	do{
		std::cin>>guess;
	}while(!Guess(guess));
	return 0;
}