# Setup

1. `$ git clone https://github.com/Antons-Skafferi-MIUN/App.git`
2. `$ cd App`
3. `$ ./install_script.sh`

# API level 24(lollipop) for tablet Nexus 9

## Git policy
Run the git-hook install script after cloning: `$./install_script.sh`. This will install a pre-push hook that will restrict push access and give hints on how to follow policy. 

# Development workflow
I recommend we use the [Feature Branch Workflow](https://www.atlassian.com/git/tutorials/comparing-workflows/feature-branch-workflow). Each new feature a person is working on should be made in a new branch. These branches should ideally never be more than a few days old as the features should be small and quick to implement. The person working on the feature will make a pull-request to merge the finished feature into the master branch once it's completed. This way the master branch will only ever contain working code. 

## Git workflow
#### No one is supposed to push directly to master! All code should go through pull-requests.
1. Select a user story from Trello, or create one if needed!
2. It's important to change to the master branch before creating new branches `$ git checkout master`
3. Remember to update your local master branch before creating new branches `$ git pull`
4. Create your new branch `$ git branch user_story_name`
5. Change to your new branch `$ git checkout user_story_name`
6. Commit your local changes __often__ `$ git commit -am "descriptive message"`
7. Push your changes to Github `$ git push origin HEAD`
8. Create a pull-request into master _only_ when your user story is implemented and tested. 

The pull-request will start a new thread where you can discuss your implementation, request code review and merge your branch into master. You can delete your branch after it has been merged into master. 

You can use `$ git fetch`to fetch a list of other people's branches. 

# Requirements
It's crucial we all use the same environment

## Mobile development

* Android Studio  
* Android 5.0 (API level 24), required for native vector graphics

# How to get huge performance increase!!
1. Open AVD manager in android studio.
2. Click "Create Virtual Device".
3. Click "New Hardware Profile".
4. Set screen size to 8.86 inches, RAM to 1024 unclick all camera support and sensors. Then click
finish.
5. Select your newly created hardware profile, it should appear under the "Tablet" category
6. Install it and use it!
