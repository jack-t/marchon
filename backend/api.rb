require "sinatra"
require "json"
require_relative "march.rb"

enable :sessions

get '/marches' do
    puts March.all
    March.all.to_json
end

post '/login' do
    @username = params[:username]
    @user = Organizer.first(:username => @username)
    content_type :json
    {:id=>@user.id}.to_json
end

get '/notifications/:march' do
    @march = March.get(params[:march])
    @notifs = @march.notifications
    @notifs.to_json
end

get '/events/:march' do
    @march = March.get(params[:march])
    @events = @march.events
    @events.to_json
end

post '/post_notification' do
    @march = March.get(params[:march]) 
    @notification = @march.notifications.create(
        :title => params[:title],
        :description => params[:description]            
    )
    @notification.save!
     
end

get '/routes/:march' do
    @march = March.first(:id => params[:march])
    @march.routes.to_json
end

get '/notifications/:march' do
    @march = March.first(:id => params[:march])
    @march.notifications.to_json
end
