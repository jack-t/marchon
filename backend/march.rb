require "data_mapper"
require "bcrypt"

DataMapper.setup(:default, 'sqlite::memory:')

class March
    include DataMapper::Resource

    property :id, Serial
    property :title, String
    property :lat, Decimal 
    property :long, Decimal 
    property :date, Date 

    has n, :events
    has n, :notifications
end

class Event
    include DataMapper::Resource

    property :id, Serial
    property :title, String
    property :description, String
    property :lat, Decimal
    property :long, Decimal
    property :time, DateTime
    belongs_to :march # FK
end

class Notification
    include DataMapper::Resource

    property :id, Serial
    property :title, String
    property :description, String
     
    belongs_to :march # FK
end

class Organizer
    include DataMapper::Resource
    include BCrypt
    property :id, Serial
    property :username, String
end

DataMapper.finalize # finish up the models
DataMapper.auto_upgrade!
# Setup initial data

user = Organizer.create(
    :username => "username"
)
user.save!

march = March.create(
    :title => "March Title",
    :lat => 100.0005,
    :long => 200,
    :date => Time.now
)
march.save!

user = Organizer.create(
    :username => "username"
)
user.save!

event = march.events.create(
    :title => "title",
    :description => "description"

)
event.save!

