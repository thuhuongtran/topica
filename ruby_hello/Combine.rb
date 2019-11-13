module MyArray
  def map(&block)
    result = []
    self.each {|e| result << block.call(e)}
    result
  end
end

class FakeArray
  include MyArray
  def initialize(*arg)
    @arr = arg.flatten
  end
  def each
    i = 0
    while i < @arr.length
      yield @arr[i]
      i += 1
    end
    @arr
  end
end
puts FakeArray.new([7,8,3,4,2]).map {|i| i*2}
puts FakeArray.new([4,5,1,2]).each {|i| i}

# exercise 3
Object.prepend(Module.new do
  def say_hello
    puts "Hi, I am a #{self.class}"
  end
end)
class Test

end
2.say_hello
[1,2].say_hello
"yay".say_hello
Test.say_hello

