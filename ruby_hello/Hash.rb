# exercise 1

class User
  def initialize(id, name)
    @id = id
    @name = name
  end
  attr_reader :id, :name
  attr_writer :id, :name
end

users = [User.new(1, "john"), User.new(2, "Kat"), User.new(3, "Kerry"), User.new(4, "Matt")]

x = gets.chomp.to_i
puts users.find{|i| i.id === x}.name

#  exercise 2
arr = [0, 1]
while arr.size <= 50
  i = arr[arr.length-1] + arr[arr.length-2]
  arr.push i
end
x = gets.chomp.to_i
puts arr[x]