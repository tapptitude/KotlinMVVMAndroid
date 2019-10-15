# KotlinMVVMAndroid
Sample android app created using Kotlin, RxJava, Dagger2, Retrofit with feature-based package structure and presentation layer built according to the MVVM architectural pattern.

----- Third party dependencies -----

Besides the Kotlin related dependencies and basic android + architecture component  ones, we decided going with the following choices :
	
	* Dagger2 and RxJava : this is a great addition for the MVVM architectural pattern, making it even more clean, improves the communication between architectural components using the reactive behaviour provided by Observables and nevertheless, the ease of testing due to dependencies injection.
	* Retrofit with OKHttp client: The best and the most reliable choice for networking.
	* Timber for the ease of logging.



----- Observations -----

This section contains our observations on the existing code which you might want to consider, and we hope these would somehow answer some of your questions : 
* The reasoning behind having an extra layer of abstraction between the XML/View and the view model is that in some cases, a view may be reused across multiple screens, and the view model should be different.
Thus, if having an interface for the communication, this can be easily changed with multiple implementations.
* Whenever observing live data from a fragment, use viewLifecycleOwner, don't use LifecycleOwner.
Check difference between viewLifecycleOwner and lifecycleOwner for more details. Look for potential problems as well.
* There's no aditional layer of abstraction between the usecase and the viewmodel because on small and medium sezied projects this may result in boilerplate. Consider separating usecases and interactors on a long run.
