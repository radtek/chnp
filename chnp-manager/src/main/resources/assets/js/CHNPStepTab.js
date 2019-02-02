/**Step-Tab plugin
 *
 * @CreateBy chngzhen@outlook.com
 * @CreateOn 2018-09-03
 * @UpdateBy
 * @UpdateOn
 *  2018-09-06 chngzhen@outlook.com
 *      Add a new function named 'afterNext' which is calling back after finishing the 'next step' event.
 *
 ********** Example for HTML: **********
 * <div class="m-steptab">
 * 	<div class="steps">
 * 		<div class="active"><span>First Step</span></div>
 * 		<div><span>Second Step</span></div>
 * 	</div>
 * 	<div class="content">
 * 		<div class="active">Panel 1</div>
 * 		<div>Panel 2</div>
 * 	</div>
 * 	<div class="operate">
 * 		<button class="btnPrevStep" disabled="disabled">Previous Step</button>
 * 		<button class="btnNextStep">Next Step</button>
 * 		<button class="btnFinish" disabled="disabled">Commit</button>
 * 	</div>
 * </div>
 *
 ********** Example for jQuery: **********
 * Steptab({
 * 	containerSelector: "div.m-steptab",
 * 	prevButtonSelector: "button.btnPrevStep",
 * 	nextButtonSelector: "button.btnNextStep",
 * 	finishButtonSelector: "button.btnFinish",
 * 	beforeNext: function(index) {
 * 		switch(index) {
 * 			case 0:
 * 				// TODO
 * 			break;
 * 			case ...
 * 		}
 * 	},
 * 	afterNext: function(index) {
 * 	    // TODO
 * 	},
 * 	beforeFinish: function() {
 * 		console.log("finish");
 * 	}
 * });
 *
 ********** Notes: **********
 * 	1. The property "containerSelector" is the jQuery selector of the container. This plugin will find other elements by it, such as $("div.m-steptab button.btnPrevStep"). The property "prevButtonSelector", "nextButtonSelector" and "finishButtonSelector" too.
 * 	2. The "index" argument in function such as "beforeNext" is based on 0. It is 0 means from step one to step two.
 */
var CHNPStepTab = function(settings) {
    var def = {
        containerSelector: "div.m-steptab",
        prevButtonSelector: "button.btnPrevStep",
        nextButtonSelector: "button.btnNextStep",
        finishButtonSelector: "button.btnFinish",
        beforeNext: function(index) {
            console.log("To trigger 'next step' event. Returning false will stop the event.");
        },
        afterNext: function(index) {
            console.log("To do something after the 'next step' event triggered.")
        },
        beforeFinish: function() {
            console.log("To trigger 'finish' event.");
        }
    };
    $.extend(true, def, settings);

    $(def.containerSelector + " " + def.prevButtonSelector).attr("disabled", true);
    $(def.containerSelector + " " + def.finishButtonSelector).attr("disabled", true);

    $.each($(def.containerSelector + " div.steps").children("div"), function(index, element) {
        $(element).append('<div style="width:0;height:0;border-width:15px 0 15px 15px;border-style:solid;border-color:transparent transparent transparent #e0e0e0;"></div>');
    });

    $(def.containerSelector + " " + def.nextButtonSelector).on("click", function(e) {
        e.preventDefault();

        var steps = $(def.containerSelector + " div.steps").children("div");
        var contents = $(def.containerSelector + " div.content").children("div");
        var flag = false;
        for(var index = 0; index < steps.length; index++) {
            if(flag) {
                // Callback the function named "onNext" in the settings.
                // If false is returned, it will stop to go next step.
                var result = def.beforeNext(index - 1);
                if(false == result) return false;

                $(steps[index - 1]).removeClass("active");
                $(contents[index - 1]).removeClass("active");
                $(steps[index]).addClass("active");
                $(contents[index]).addClass("active");

                if (0 < index) $(def.containerSelector + " " + def.prevButtonSelector).attr("disabled", false);
                if (steps.length - 1 == index) {
                    $(this).attr("disabled", true);
                    $(def.containerSelector + " " + def.finishButtonSelector).attr("disabled", false);
                }

                def.afterNext(index - 1);
                return false;
            }
            flag = $(steps[index]).hasClass("active");
        }
    });

    $(def.containerSelector + " " + def.prevButtonSelector).on("click", function(e) {
        e.preventDefault();

        var steps = $(def.containerSelector + " div.steps").children("div");
        var contents = $(def.containerSelector + " div.content").children("div");
        var flag = false;
        for(var index = steps.length - 1; index >= 0; index--) {
            if(flag) {
                $(steps[index + 1]).removeClass("active");
                $(contents[index + 1]).removeClass("active");
                $(steps[index]).addClass("active");
                $(contents[index]).addClass("active");

                if (0 == index) $(def.containerSelector + " " + def.prevButtonSelector).attr("disabled", true);

                $(def.containerSelector + " " + def.nextButtonSelector).attr("disabled", false);
                $(def.containerSelector + " " + def.finishButtonSelector).attr("disabled", true);

                return false;
            }
            flag = $(steps[index]).hasClass("active");
        }
    });

    $(def.containerSelector + " " + def.finishButtonSelector).on("click", function(e) {
        e.preventDefault();
        def.beforeFinish();
    });

};